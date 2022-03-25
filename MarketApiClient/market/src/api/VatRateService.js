import axios from "axios";

class VatRateService{

    updateVatRate(data){

        const url = "api/v1/vat-rates"

        return axios.put(url, data);
    }

}

export default new VatRateService();