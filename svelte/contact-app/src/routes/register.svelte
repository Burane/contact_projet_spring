<!-- src/routes/index.svelte -->
<svelte:head>
    <title>Login</title>
</svelte:head>

<div class="text-center">
    <main class="form-signin">
        <form>
            <img class="mb-4" src="images/logo.png" alt="" width="72" height="72">
            <h1 class="h3 mb-3 fw-normal">Please sign up</h1>
            <div class="form-floating">
                <input type="text" name="username" class="form-control" id="floatingInput" placeholder="Username">
                <label for="floatingInput">Username</label>
            </div>
            <div class="form-floating">
                <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
                <label for="floatingPassword">Password</label>
            </div>

            <div class="checkbox mb-3">
                <label>
                    <a href="login">Already have an account ?</a>
                </label>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">Sign Up</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
        </form>
    </main>
</div>

<style>

    body {
        display: -ms-flexbox;
        display: flex;
        -ms-flex-align: center;
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
    .form-signin .form-control {
        position: relative;
        box-sizing: border-box;
        height: auto;
        padding: 10px;
        font-size: 16px;
    }
    .form-signin .form-control:focus {
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

    .form-signin input[type="text"] {
        margin-bottom: 10px;
    }
</style>