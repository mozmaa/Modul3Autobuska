import { useEffect, useState } from "react"
import { Form, FormSelect } from "react-bootstrap"
import { Button } from "react-bootstrap"
import { Col } from "react-bootstrap"
import { Row } from "react-bootstrap"
import TestAxios from "../../apis/TestAxios"
import { useNavigate } from "react-router-dom"

export const EditLinija = (props) => {

    const [editLinija, setEditLinija] = useState(props.selectedLinija)
    const [prevoznici, setPrevoznici] = useState([])
    const navigate=useNavigate()

    const getPrevoznici = () => {
        TestAxios.get('/prevoznici')
            .then(res => {
                setPrevoznici(res.data)
            }).catch(error => {
                alert('Doslo je do greske!')
            })
    }

    useEffect(() => {
        getPrevoznici()
    }, [])

    const handleChange = (e) => {
        
        setEditLinija({ ...editLinija, [e.target.name]: e.target.value })
    }

    const edit = (id) => {
        TestAxios.put(`/linije/${id}` , editLinija)
            .then(res => {
                alert('Uspesna izmena')
                navigate('/linije')
            }).catch (error => {
                console.log(error)
                alert('Doslo je do greske')
            })
    }

    console.log(props)
    return (
        <Row className="justify-content-center">
            <Col md={8}>
                <h1>Dodavanje</h1>
                <Form.Group>
                    <Form.Label>Broj mesta</Form.Label>
                    <Form.Control type='number' name='brojMesta' min={1} max={80} value={editLinija.brojMesta} onChange={(e) => handleChange(e)} ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Cena karte</Form.Label>
                    <Form.Control type='number' name='cenaKarte' min={10} value={editLinija.cenaKarte} onChange={(e) => handleChange(e)} ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Destinacija</Form.Label>
                    <Form.Control type='text' name='destinacija' value={editLinija.destinacija} onChange={(e) => handleChange(e)} ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Vreme polaska</Form.Label>
                    <Form.Control type='time' name='vremePolaska' value={editLinija.vremePolaska} onChange={(e) => handleChange(e)} ></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Prevoznik</Form.Label>
                    <FormSelect name="prevoznikId" value={editLinija.prevoznik} onChange={(e) => handleChange(e)}>
                       
                        {prevoznici.map((prevoznik, index) => {
                            return (
                                <option key={index} value={prevoznik.id}>{prevoznik.naziv}</option>
                            )
                        })
                        }
                    </FormSelect>
                </Form.Group>
                <Button style={{ marginTop: 10, marginBottom: 10 }} onClick={() => {edit(editLinija.id)}}>Edit</Button>
            </Col>
        </Row>
    )
}