<template>
  <div class="text-center">
    <main class="form-signin">
      <Form @submit="handleRegister" :validation-schema="schema">
        <img class="mb-4" src="../assets/images/userLogo.png" alt="" width="72" height="72">
        <h1 class="h3 mb-3 fw-normal">Please sign up</h1>

        <div class="form-floating">
          <Field type="text" name="username" class="form-control" id="username"
                 placeholder="Username"/>
          <label for="username">Username</label>
          <ErrorMessage name="username" class="error-feedback"/>
        </div>

        <div class="form-floating">
          <Field type="password" name="password" class="form-control" id="password"
                 placeholder="Password"/>
          <label for="password">Password</label>
          <ErrorMessage name="password" class="error-feedback"/>

        </div>

        <div class="checkbox mb-3">
          <label>
            <router-link to="/login">Already have an account ?</router-link>
          </label>
        </div>

        <button class="btn btn-primary btn-block" v-on:submit.prevent="handleRegister" :disabled="loading">
            <span
                v-show="loading"
                class="spinner-border spinner-border-sm"
            ></span>
          <span>Sign up</span>
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
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";

export default {
  name: "Register",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      username: yup
          .string()
          .required("Username is required!")
          .min(3, "Must be at least 3 characters!")
          .max(20, "Must be maximum 20 characters!"),
      password: yup
          .string()
          .required("Password is required!")
          .min(6, "Must be at least 6 characters!")
          .max(40, "Must be maximum 40 characters!"),
    });

    return {
      successful: false,
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
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  },
  methods: {
    handleRegister(user) {
      this.message = "";
      this.successful = false;
      this.loading = true;

      this.$store.dispatch("auth/register", user).then(
          () => {
            this.$router.push("/login");
          },
          (error) => {
            this.message =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();
            this.successful = false;
            this.loading = false;
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