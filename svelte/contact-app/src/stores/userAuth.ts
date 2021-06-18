import {writable} from 'svelte/store';
import Auth from "../objects/Auth";
import {Deserialize} from "cerialize";


export const authStore = writable(getAuthCookie());
authStore.subscribe((auth) => {
    if (auth) {
        setAuthCookie(auth)
    } else {
        deleteAuthCookie()
    }
})

export function logout() {
    authStore.set(null)
}

export async function login(username, password) {
    let res: Response = await fetch("/api/auth/login", {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            username,
            password
        })
    })

    const json = await res.json()
    if (res.status == 200) {
        let auth: Auth = Deserialize(json, Auth);
        authStore.set(auth)
    } else {
        return Promise.reject(json?.message)
    }
}


function setAuthCookie(auth) {
    document.cookie = `auth=${JSON.stringify(auth)}; path="/"`
}

function deleteAuthCookie() {
    deleteCookie('auth')
}

function getAuthCookie() {

    try {
        let auth: Auth = JSON.parse(getCookie('auth'))
        return auth
    } catch (e) {
        return null
    }
}

function getCookie(name) {
    if (document.cookie.length > 0) {
        let c_start = document.cookie.indexOf(name + "=");
        if (c_start != -1) {
            c_start = c_start + name.length + 1;
            let c_end = document.cookie.indexOf(";", c_start);
            if (c_end == -1) {
                c_end = document.cookie.length;
            }
            return unescape(document.cookie.substring(c_start, c_end));
        }
    }
    throw new Error("No corresponding cookie found")
}

function deleteCookie(name) {
    document.cookie = name + '=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}