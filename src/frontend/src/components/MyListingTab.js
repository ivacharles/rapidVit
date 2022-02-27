import React from "react";
import {
    Container,
    Nav,
    Navbar,
} from "react-bootstrap";
import {NavLink} from "react-router-dom";
import {AiFillCaretRight} from "react-icons/ai";

function MyListingTab() {
    return (
        <Container className={"sub-nav-bg-color"}>
            <Nav className="justify-content-start" activeKey="/home">
                <Nav.Item>
                    <NavLink to="/create-listing" className="nav-link  text-white fw-bolder text-nowrap">
                        <AiFillCaretRight /> Kreye yon post
                    </NavLink>
                </Nav.Item>
                <Nav.Item>
                    <NavLink to="/create-listing" className="nav-link  text-white fw-bolder text-nowrap">
                        <AiFillCaretRight /> Modifye yon post ki existe
                    </NavLink>
                </Nav.Item>
            </Nav>

        </Container>
    );
}

export default MyListingTab;
