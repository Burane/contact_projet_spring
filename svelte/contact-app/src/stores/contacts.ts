import {writable} from 'svelte/store';
import type Contact from "../objects/Contact";

const contactStore = writable(Array<Contact>(0));

export default contactStore;