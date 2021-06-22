<template>

  <AddContact></AddContact>
  <div class="card" v-for="contact in content">
    <div class="card-header">
      Contact :
      <font-awesome-icon @click="deleteContact(contact)" class="float-end delete" icon="trash-alt"/>
      <font-awesome-icon class="float-end edit" icon="edit" />
    </div>
    <div class="card-body">
      <h5 class="card-title"> {{ contact.firstName }} {{ contact.lastName }}</h5>
      <div></div>
      <Emails :infos="{id: contact._id+'_mail', emails : contact.emails}"></Emails>
      <Address :infos="{id: contact._id+'_addr', address : contact.address}"></Address>
    </div>
  </div>
</template>

<script>
import UserService from "../services/user.service";
import Emails from "./Emails.vue";
import Address from "./Address.vue";
import AddContact from "./AddContact.vue";

export default {
  name: "Dashboard",
  components: {AddContact, Address, Emails},
  data() {
    return {
      content: [],
    };
  },
  mounted() {
    this.getContact()
  },
  methods : {
    deleteContact(contact) {
        console.log(contact)
        UserService.deleteContact(contact).then(response => {
          console.log(response)
        })
      this.getContact()
    },
    getContact() {
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
    }
  }
};
</script>

<style scoped>

.delete, .edit {
  margin: 5px;
  cursor: pointer;
}

.delete:hover {
  color: red;
  transition-duration: 150ms;
}

.edit:hover {
  color: orange;
  transition-duration: 150ms;
}

</style>