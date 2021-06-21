<template>
  <div class="col-md-12">
    <div class="card card-container">

      <Form @submit="handleLogin" :validation-schema="schema">
        <div class="form-group">
          <label for="username">Username</label>
          <Field name="username" id="username" type="text" class="form-control"/>
          <ErrorMessage name="username" class="error-feedback"/>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <Field name="password" id="password" type="password" class="form-control"/>
          <ErrorMessage name="password" class="error-feedback"/>
        </div>

        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading">
            <span
                v-show="loading"
                class="spinner-border spinner-border-sm"
            ></span>
            <span>Login</span>
          </button>
        </div>

        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">
            {{ message }}
          </div>
        </div>
      </Form>
    </div>
  </div>

  <!--login-->

  <div class="text-center">
    <main class="form-signin">
      <Form @submit="handleLogin" :validation-schema="schema">
        <img class="mb-4" src="../assets/images/userLogo.png" alt="" width="72" height="72">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <div class="form-floating">

          <label for="username">Username</label>
          <input type="text" name="username" class="form-control" id="username"
                 placeholder="Username">
          <ErrorMessage name="username" class="error-feedback"/>
        </div>

        <div class="form-floating">
          <label for="password">Password</label>
          <input type="password" name="password" class="form-control" id="password"
                 placeholder="Password">
          <ErrorMessage name="password" class="error-feedback"/>

        </div>

        <div class="checkbox mb-3">
          <label>
            <a href="register">Dont have an account yet ?</a>
          </label>
        </div>
        <button class="btn btn-primary btn-block" :disabled="loading">
            <span
                v-show="loading"
                class="spinner-border spinner-border-sm"
            ></span>
          <span>Login</span>
        </button>
        <div v-if="message" class="alert alert-danger" role="alert">
          {{ message }}
        </div>
        <p class="mt-5 mb-3 text-muted">&copy; 2017–2021</p>
      </Form>
    </main>
  </div>
</template>

<script>
import {Form, Field, ErrorMessage} from "vee-validate";
import * as yup from "yup";

export default {
  name: "Login",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      username: yup.string().required("Username is required!"),
      password: yup.string().required("Password is required!"),
    });

    return {
      loading: false,
      message: "",
      schema,
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  },
  methods: {
    handleLogin(user) {
      this.loading = true;

      this.$store.dispatch("auth/login", user).then(
          () => {
            this.$router.push("/profile");
          },
          (error) => {
            this.loading = false;
            this.message =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();
          }
      );
    },
  },
};
</script>


<style scoped>
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