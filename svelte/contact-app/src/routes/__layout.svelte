<script lang="ts">
    import Nav from "../components/Nav.svelte";
    import {goto} from '$app/navigation';

    import {afterUpdate, onMount} from "svelte";

    let authStore
    let logout
    onMount(async () => {
        console.log("onMount")
        const module = await import('../stores/userAuth');
        authStore = module.authStore
        logout = module.logout

    });

    afterUpdate(() => {
        console.log("afterUpdate")
        if (!$authStore && ['/login','/register','/'].find(val => val==window.location.pathname) === undefined) {
            goto('/login')
        }
    });

</script>

<Nav/>
<slot></slot>