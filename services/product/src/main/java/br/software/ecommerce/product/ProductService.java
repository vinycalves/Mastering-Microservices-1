package br.software.ecommerce.product;

import br.software.ecommerce.exception.ProductNotFoundException;
import br.software.ecommerce.exception.ProductPurchaseException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public UUID createProduct(@Valid ProductRequest request) {
        var product = productRepository.save(productMapper.toProduct(request));
        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        var productIds = requests.stream().map(ProductPurchaseRequest::productId).toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if (storedProducts.size() != requests.size()) {
            throw new ProductPurchaseException("One or more products, doesn't exist");
        }

        var storedRequest = requests.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity())
                throw new ProductPurchaseException("Inssure quantity for product " + product.getId() + " is less than " + productRequest.quantity());
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toProductResponse);
    }

    public ProductResponse getProductById(UUID productId) {
        return productRepository.findById(productId).map(productMapper::toProductResponse).orElseThrow(() -> new ProductNotFoundException("The product was not found"));
    }
}
