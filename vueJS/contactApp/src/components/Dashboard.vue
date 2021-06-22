<template>

  <div class="d-flex justify-content-center">
    <button class="btn btn-circle btn-xl btn-primary" @click="updateModal(null)">
      <font-awesome-icon icon="plus"></font-awesome-icon>
    </button>
  </div>

  <AddOrModifyModal :contact="currentContact" ref="addOrModifyModal"></AddOrModifyModal>

  <div class="card" v-for="contact in content">
    <div class="card-header">
      Contact :
      <a @click.prevent="deleteContact(contact)">
        <font-awesome-icon class="float-end delete" icon="trash-alt"/>
      </a>
      <a @click.prevent="updateModal(contact)">
        <font-awesome-icon class="float-end edit" icon="edit"/>
      </a>
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
import AddOrModifyModal from "./AddOrModifyModal.vue";

export default {
  name: "Dashboard",
  components: {AddOrModifyModal, Address, Emails},
  data() {
    return {
      content: [],
      currentContact: {}
    };
  },
  mounted() {
    this.getContact()
  },
  methods: {
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
    },
    updateModal(contact) {
      this.changeCurrentContact(contact)
      this.showModal()
    },
    changeCurrentContact(contact) {
      this.currentContact = contact
    },
    showModal() {
      this.$refs.addOrModifyModal.showModal()
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

button {
  margin: 1em;
}

.btn-circle.btn-xl {
  width: 70px;
  height: 70px;
  padding: 10px 16px;
  border-radius: 35px;
  font-size: 24px;
  line-height: 1.33;
}

.btn-circle {
  width: 30px;
  height: 30px;
  padding: 6px 0px;
  border-radius: 15px;
  text-align: center;
  font-size: 12px;
  line-height: 1.42857;
}
</style>