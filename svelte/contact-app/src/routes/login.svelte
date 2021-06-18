<svelte:head>
    <title>Login</title>
</svelte:head>

<div class="text-center">
    <main class="form-signin">
        <form on:submit|preventDefault={submit}>
            <img class="mb-4" src="images/logo.png" alt="" width="72" height="72">
            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
            {#if error}
                <div>
                    {message}
                </div>
            {/if}

            <div class="form-floating">
                <input bind:value={username} type="text" name="username" class="form-control" id="username"
                       placeholder="Username">
                <label for="username">Username</label>
            </div>
            <div class="form-floating">
                <input bind:value={password} type="password" name="password" class="form-control" id="password"
                       placeholder="Password">
                <label for="password">Password</label>
            </div>

            <div class="checkbox mb-3">
                <label>
                    <a href="register">Dont have an account yet ?</a>
                </label>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
        </form>
    </main>
</div>

<script lang="ts">
    import {goto} from '$app/navigation';
    import {login} from '/src/stores/userAuth'

    let username: string
    let password: string
    let error: boolean = false
    let message: string


    const submit = async () => {

        try {
            await login(username, password)
            await goto('/dashboard')
        } catch (err) {
            error = true
            message = err
        }


    }
</script>


<style>
    html,
    body {
        height: 100%;
    }

    body {
        display: flex;
        align-items: center;
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
    }

    .form-signin {
        width: 100%;
        max-width: 330px;
        padding: 15px;
        margin: auto;
    }

    .form-signin .checkbox {
        font-weight: 400;
    }

    .form-signin .form-floating:focus-within {
        z-index: 2;
    }

    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }

    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>