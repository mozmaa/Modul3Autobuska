import { Form, FormSelect } from "react-bootstrap"
import { Button } from "react-bootstrap"
import { Col } from "react-bootstrap"
import { Row } from "react-bootstrap"

export const Search = (props) => {


    const handleChange = (e) => {
        props.searchParamsCallback({...props.searchParams, [e.target.name]: e.target.value})
    }

    return (

        
            
        <Row className="justify-content-center">
            <Col md={8}>
                <Form.Group>
                    <Form.Label>Destinacija</Form.Label>
                    <Form.Control type="text" name='destinacija' onChange={(e) => handleChange(e)}></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Prevoznik</Form.Label>
                    <FormSelect name="prevoznikId" onChange={(e) => handleChange(e)}>
                        <option value=''>--</option>
                        {props.prevoznici.map((prevoznik, index)=> {
                            return(
                                <option key={index} value={prevoznik.id}>{prevoznik.naziv}</option>
                            )
                        })
                    }   
                    </FormSelect>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Maksimalna cena</Form.Label>
                    <Form.Control type="number" name='maxCena'   onChange={(e) => handleChange(e)}></Form.Control>
                </Form.Group>
                
                <Button style={{ marginTop: 10, marginBottom: 10 }} onClick={() => props.getLinijeCallback(0)}>Search</Button>
            </Col>
        </Row>
    )
}