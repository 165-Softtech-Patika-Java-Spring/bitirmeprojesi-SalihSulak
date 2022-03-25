  import './App.css';
  import { Route, Routes } from 'react-router-dom';
import LoginPage from './components/login/LoginPage';
import RegisterPage from './components/register/RegisterPage';
import ListStats from './components/product/ListStats';
import ListAllProducts from './components/product/ListAllProductsPage';
import React from 'react';
import AddProductPage from './components/product/AddProductPage';
import UpdateProduct from './components/product/UpdateProductPage';
import Menu from './components/menu/Menu';
import SearchWithFilter from './components/product/SearchWithFilterPage';
import ListProductsByProductType from './components/product/ListProductsByProductTypePage';
import ListProductsByPrice from './components/product/ListProductsByPrice';
import UpdateVatRate from './components/vatrate/UpdateVatRatePage';


class App extends React.Component {
  constructor(props){
    super(props);

    this.state = {
      isLoggedOn: false
    }

    this.login = this.login.bind(this);
    this.logout = this.logout.bind(this);
  }

  login(){
    this.setState({isLoggedOn: true})
  }

  logout(){
    this.setState({isLoggedOn: false})
    sessionStorage.clear();
  }

  getIsLogged(){

    const token = sessionStorage.getItem('token');

    const isLogged = token ? true : false;

    return isLogged;
  }

  render(){
    const isLogged = this.getIsLogged();

  return (
    <div className="App">

    <Menu isLoggedOn={isLogged} logout={this.logout}></Menu>


      <Routes>
          <Route path='/login' element={<LoginPage></LoginPage>}></Route>
          <Route path='/register' element={<RegisterPage></RegisterPage>}></Route>
          <Route path='/products/listStats' element={<ListStats></ListStats>}></Route>
          <Route path='/products/add' element={<AddProductPage></AddProductPage>}></Route>
          <Route path='/products/all' element={<ListAllProducts></ListAllProducts>}></Route>
          <Route path='/products/update/:id' element={<UpdateProduct></UpdateProduct>}></Route>
          <Route path='/' element={<SearchWithFilter></SearchWithFilter>}></Route>
          <Route path='/products/productType' element={<ListProductsByProductType></ListProductsByProductType>}></Route>
          <Route path='/products/price' element={<ListProductsByPrice></ListProductsByPrice>}></Route>
          <Route path='/vat-rates/update' element={<UpdateVatRate></UpdateVatRate>}></Route>

        </Routes>
        </div>
  );
}
}

export default App;
