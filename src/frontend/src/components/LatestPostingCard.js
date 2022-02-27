import React from "react";
import {Button, Card, Col} from "react-bootstrap";


function LatestPostingCard(props) {
    return(
        <Card style={{ width: '18rem'}} className={"text-center bg-light  border-0"}>
            <Card.Img src={props.bannerIMG} variant={"top"} className={"img-fluid"}/>
            <Card.Body>
                <Card.Text>
                    {props.bannerTitle}
                </Card.Text>
            </Card.Body>
            <Card.Footer>
                <small className="text-muted">{props.bannerDesc}</small>
            </Card.Footer>
        </Card>
    );
}
export default LatestPostingCard;