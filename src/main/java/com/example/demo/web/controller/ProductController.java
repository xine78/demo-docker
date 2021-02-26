package com.example.demo.web.controller;

import com.example.demo.dao.ProductDao;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductDao productDao;

    @GetMapping(value = "/probe")
    public ResponseEntity<String> probe() {
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }

    // Affiche la liste de tous les produits disponibles
    @GetMapping(value = "/Produits")
    public ResponseEntity<List<Product>> listeDesProduits() {
        Optional<List<Product>> optLst = Optional.of(productDao.findAll());

        if(optLst.isPresent() && optLst.get().isEmpty()) {
            productDao.save(new Product(1, "Titre_1", "description_1", null, 12.35));
            productDao.save(new Product(2, "Titre_2", "description_2", null, 22.44));
            productDao.save(new Product(3, "Titre_3", "description_3", null, 32.33));
            productDao.save(new Product(4, "Titre_4", "description_4", null, 42.44));
            productDao.save(new Product(5, "Titre_5", "description_5", null, 55.55));
            productDao.save(new Product(6, "Titre_6", "description_6", null, 62.65));

            optLst = Optional.of(productDao.findAll());
        }
        if(optLst.isPresent() && optLst.get().isEmpty()) throw new RuntimeException("Aucun produit trouvé");

        return new ResponseEntity<List<Product>>(optLst.get(), HttpStatus.OK);
    }
}