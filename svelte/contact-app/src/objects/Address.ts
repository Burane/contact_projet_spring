import {deserialize} from "cerialize";

class Address {
    @deserialize _id: string;
    @deserialize postalCode: number;
    @deserialize city: string;
    @deserialize street: string;

};

export default Address;