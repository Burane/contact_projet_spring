<Card>
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
            <p>
                {adr.street}
                {adr.postalCode}
                {adr.city}
            </p>
        {/each}
    {/if}


    <div class="card" style="width: 18rem;">
        <div class="card-header">
            Informations
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">An item</li>
            <li class="list-group-item">A second item</li>
            <li class="list-group-item">A third item</li>
        </ul>
    </div>


</Card>

<script lang="ts">
    import Card from '../shared/Card.svelte';
    export let contact;
    console.log(contact);
</script>