import {writable} from 'svelte/store';
import Auth from "../objects/Auth";

const authStore = writable(Auth);

export default authStore;