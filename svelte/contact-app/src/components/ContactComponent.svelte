<Card>
    <br><br>
    <div class="contact">
        {contact._id}
    </div>
    {#if contact.emails}
        {#each contact.emails as email }
            <p>
                {email.email}
            </p>
        {/each}
    {/if}

    {#if contact.address}
        {#each contact.address as adr }
            <div class="card-header">
                Informations
            </div>
            <ul class="list-group list-group-flush" id="dataContact">
                <label>Rue : </label>
                <li class="list-group-item">{adr.street}</li>
                <label>Code Postal : </label>
                <li class="list-group-item">{adr.postalCode}</li>
                <label>Ville : </label>
                <li class="list-group-item">{adr.city}</li>
            </ul>

        {/each}
    {/if}

</Card>

<script lang="ts">
    import Card from '../shared/Card.svelte';
    export let contact;
    console.log(contact);
</script>

<style>
    #dataContact{
        display: inline;
    }

    label{
        font-weight: bold;
    }

</style>