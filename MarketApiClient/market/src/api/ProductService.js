import axios from "axios";

class ProductService{

    add(product){
        const url = "/api/v1/products";

        return axios.post(url, product);
    }
    listAll(){
        const url = "/api/v1/products";

        return axios.get(url);

    }

    listStats(productType){
        const url = "/api/v1/products/"+productType+"/analysis";

        return axios.get(url);
    }

    updateProduct(product){
        const url = "/api/v1/products";

        return axios.put(url,product);
    }

    getByProductType(productType){
        const url = "/api/v1/products/"+productType;

        return axios.get(url,productType);
    }

    listByPrice(minPrice, maxPrice){
        const url = "/api/v1/products/";
        return axios.get(url,{ params: { start_price: minPrice, end_price:maxPrice } });
    }

}
export default new ProductService();
