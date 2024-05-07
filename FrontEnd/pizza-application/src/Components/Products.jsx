import React, { useEffect, useState } from "react";
import Product from "./Product";
import { useNavigate } from "react-router-dom";

const BASEURL = "http://localhost:8080/getAllPizza";

const Products = () => {
  const [products, setProducts] = useState([]);
  let navigate =useNavigate()

  useEffect(() => {
    const fetchDataFromApi = async () => {
      try {
        const response = await fetch(BASEURL);
        const data = await response.json();
        setProducts(data);     //[{},{},{}]
      } catch (error) {
        console.error("Error fetching data:", error);
        navigate("/error");
      }
    };

    fetchDataFromApi();
  
    
  }, []);

  return (
    <div className="container mx-auto my-1 px-4 ">
      <h1 className="text-lg font-extrabold my-8 text-center text-yellow-700">Products</h1>
      <div className="grid grid-cols-5 gap-8">
        {products.map((product) => (
          <Product key={product.id} data={product} />
        ))}
      </div>
    </div>
  );
};

export default Products;
