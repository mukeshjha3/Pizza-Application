import React, { useContext, useEffect, useState } from "react";
import { Link, NavLink } from "react-router-dom";
import CartContext from "../CartContext";

const Navbar = () => {
  const cartStyle = {
    background: "#F59E0D",
    display: "flex",
    padding: "6px 12px",
    borderRadius: "50px",
  };

  const {newcart,setNewcart} = useContext(CartContext);

  return (
    <>
      <nav className="container mx-auto flex items-center justify-between py-4 px-5 bg-orange-400 text-primary-content">
        <NavLink to="/">
          <img style={{ height: 45 }} src="/images/logo.png" alt="logo" />
        </NavLink>
        <ul className="flex items-center">
          <li>
            <NavLink
              to="/"
              className="text-white font-bold"
              style={({ isActive }) => ({
                color: isActive ? "orangered" : "white",
                textDecoration: "none",
              })}
            >
              Home
            </NavLink>
          </li>
          <li className="ml-6">
            <NavLink
              to="/products"
              className="text-white font-bold"
              style={({ isActive }) => ({
                color: isActive ? "orangered" : "white",
                textDecoration: "none",
              })}
            >
              Products
            </NavLink>
          </li>
          <li className="ml-6">
            <NavLink to="/cart">
              <div style={cartStyle}>
                <span>{newcart.length}</span>
                <img className="ml-2" src="/images/cart.png" alt="cart-icon" />
              </div>
            </NavLink>
          </li>
        </ul>
      </nav>
    </>
  );
};

export default Navbar;
