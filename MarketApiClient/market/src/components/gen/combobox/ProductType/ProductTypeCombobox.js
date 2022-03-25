import React from "react";
import Combobox from "../Combobox";

class ProductTypeCombobox extends React.Component{

    render(){
        return(
            <Combobox
                fieldName = {this.props.fieldName}
                notNull= {this.props.notNull}
                items = {[
                            {id: "FOOD", name: "FOOD"}, 
                            {id: "STATIONARY", name: "STATIONARY"},
                            {id: "CLOTHES", name: "CLOTHES"},
                            {id: "TECHNOLOGY", name: "TECHNOLOGY"}, 
                            {id: "CLEANING", name: "CLEANING"},
                            {id: "OTHER", name: "OTHER"}
                        ]}   
            ></Combobox>
        )
    }
}

export default ProductTypeCombobox;