<template>
  <div class="card" v-for="contact in content">
    <div class="card-header">
      Contact :
    </div>
    <div class="card-body">
      <h5 class="card-title"> {{ contact.firstName }} {{ contact.lastName }}</h5>
      <div></div>
        <Emails v-bind:emails="contact.emails"></Emails>
      <Address v-bind:address="contact.address"></Address>
    </div>
  </div>
</template>

<script>
import UserService from "../services/user.service";
import Emails from "./Emails.vue";
import Address from "./Address.vue";

export default {
  name: "Dashboard",
  components: {Address, Emails},
  data() {
    return {
      content: [],
    };
  },
  mounted() {
    UserService.getContact().then((response) => {
          this.content = response.data;
        },
        (error) => {
          this.content =
              (error.response &&
                  error.response.data &&
                  error.response.data.message) ||
              error.message ||
              error.toString();
        }
    );
  },
};
</script>