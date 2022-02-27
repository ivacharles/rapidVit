import React from "react";
import {
  Container,
  Nav,
  Navbar,
} from "react-bootstrap";
import NavDropdownCustom from "./NavDropdownCustom";

function SubMenu() {
  return (
      <Navbar className={"sub-nav-bg-color"}>
        <Container fluid="md" >
          <Nav className={"m-auto"}>
            <NavDropdownCustom bigTitle="Shop" />
            <NavDropdownCustom bigTitle="Housing" />
            <NavDropdownCustom bigTitle="Services" />
            <NavDropdownCustom bigTitle="Job" />
            <NavDropdownCustom bigTitle="Events" />
            <Nav.Link href="#action1" className="text-white text-nowrap">News</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
  );
}

export default SubMenu;
