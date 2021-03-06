package io.github.emvnuel.statefulapp.product;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductRepository repository;

    @SneakyThrows
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> save(@Valid @RequestPart(name = "product") ProductRequest productRequest,
                                     @RequestParam(name="file") MultipartFile image) {

        Product product = repository.save(productRequest.toModel());

        BufferedImage img = ImageIO.read(image.getInputStream());
        File file = new File("/var/images/"+product.getId() + ".jpg" );
        ImageIO.write(img, "jpg", file);

        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {

        List<Product> products = repository.findAll();

        List<ProductResponse> response = products.stream().map(p -> {

            String base64 = null;
            try {
                File file = new File("/var/images/" + p.getId()+".jpg");
                BufferedImage image = ImageIO.read(file);
                final ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", os);
                base64 = Base64.getEncoder().encodeToString(os.toByteArray());

            } catch (Exception e) {
                log.error("Error reading product image");
                log.error("{}", e.getMessage());
            }

            return new ProductResponse(p.getId(),
                    p.getName(),
                    p.getDescription(),
                    p.getPrice(),
                    base64);

        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}
