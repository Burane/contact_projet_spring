import {writable} from 'svelte/store';
import Auth from "../objects/Auth";

const authStore = writable(Auth.getAuthCookie() || Auth);

export default authStore;