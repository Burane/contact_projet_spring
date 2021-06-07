import {deserialize} from "cerialize";

class Email {
    @deserialize _id: string;
    @deserialize email: string;

};

export default Email;