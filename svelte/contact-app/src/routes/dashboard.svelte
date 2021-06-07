<!-- src/routes/index.svelte -->
<svelte:head>
    <title>Dashboard</title>
</svelte:head>

<h1>Welcome {$authStore.username}</h1>

<div class="contact-list">
    {#each $contactStore as contact (contact._id)}
        <div>
            <Contact {contact}/>
        </div>
    {/each}
</div>


<script lang="ts">
    import authStore from "../stores/userAuth";
    import contactStore from "../stores/contacts";
    import {onMount} from 'svelte';
    import Contact from "../objects/Contact";
    import {Deserialize} from "cerialize";

    onMount(async () => {
        const res = await fetch('/api/contact/all', {
            method: 'post',
            headers: new Headers({
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '+$authStore.token
            })
        });
        const json = await res.json();
        let contact: Contact = Deserialize(json, Contact)
        contactStore.update(curr => {
            return [curr, ...contact];
        })
    });


</script>