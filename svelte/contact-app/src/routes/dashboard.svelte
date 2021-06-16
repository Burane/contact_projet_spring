<!-- src/routes/index.svelte -->
<svelte:head>
    <title>Dashboard</title>
</svelte:head>

<h1>Welcome {$authStore.username}</h1>


<!-- ////////////////////////  -->
<div>
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">Ajouter contact</button>
</div>

<!-- MODAL  -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ajout de contact</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="mb-3">
                        <label for="postalCode" class="col-form-label">Code Postal:</label>
                        <input type="text" class="form-control" id="postalCode" name="postalCode">
                    </div>
                    <div class="mb-3">
                        <label for="city" class="col-form-label">Ville:</label>
                        <input type="text" class="form-control" id="city" name="city">
                    </div>
                    <div class="mb-3">
                        <label for="street" class="col-form-label">Rue:</label>
                        <input type="text" class="form-control" id="street" name="street">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="col-form-label">Email:</label>
                        <input type="email" class="form-control" id="email" name="email">
                    </div>
                    <div class="mb-3">
                        <label for="message-text" class="col-form-label">Recap:</label>
                        <textarea class="form-control" id="message-text"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-primary">Ajouter</button>
            </div>
        </div>
    </div>
</div>
<!-- END MODAL -->

<div class="contact-list">
    {#each $contactStore as contact (contact._id)}
        <div>
            <ContactComponent contact={contact}/>
        </div>
    {/each}
</div>



<script lang="ts">
    import authStore from "../stores/userAuth";
    import contactStore from "../stores/contacts";
    import {onMount} from 'svelte';
    import Contact from "../objects/Contact";
    import {Deserialize} from "cerialize";
    import ContactComponent from "../components/ContactComponent.svelte";


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
        console.log(contact)
        contactStore.update(curr => {
            return [curr, ...contact];
        })
    });

    // JS



</script>