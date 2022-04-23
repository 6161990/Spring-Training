package productImporter.suppliers.starkIndustries;

import productImporter.ProductImporter;
import productImporter.Product;

import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class StarkIndustriesProductImporter implements ProductImporter {

    private final StarkIndustriesProductSource productSource;
    private final StarkIndustriesProductTranslator translator;

    public StarkIndustriesProductImporter(StarkIndustriesProductSource productSource, StarkIndustriesProductTranslator translator) {
        this.productSource = productSource;
        this.translator = translator;
    }

    @Override
    public Iterable<Product> fetchProducts() {
        var products = new ArrayList<Product>();
        for(StarkIndustriesProduct s : productSource.getAllProducts()){
            products.add(translator.translate(s));
        }
        return products;
    }
}
