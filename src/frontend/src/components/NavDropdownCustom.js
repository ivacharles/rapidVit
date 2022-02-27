import React from "react";
import {
    Container,
    Nav,
    Navbar,
    NavDropdown,
    Row, Col, Dropdown, NavLink,
} from "react-bootstrap";
import {FaBell} from "react-icons/fa"

function NavDropdownCustom(props) {
    return (
        <NavDropdown
            id="nav-dropdown-custom"
            title={props.bigTitle}
        >
            <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
            <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
            <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
            <NavDropdown.Divider/>
            <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
        </NavDropdown>
    );
}

export default NavDropdownCustom;
