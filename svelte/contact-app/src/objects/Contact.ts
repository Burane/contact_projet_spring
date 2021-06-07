import type Address from "./Address";
import type Email from "./Email";
import {deserialize} from "cerialize";


class Contact {
    @deserialize _id: string;
    @deserialize address: Address[];
    @deserialize emails: Email[];

};

export default Contact;