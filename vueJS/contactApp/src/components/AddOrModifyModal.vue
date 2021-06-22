<template>


  <!-- Modal -->
  <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addOrModifyModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addOrModifyModal">Ajouter un contact</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <!--          body-->

          <div class="input-group mb-3">
            <span class="input-group-text"><font-awesome-icon icon="user"></font-awesome-icon></span>
            <input type="text" class="form-control" placeholder="Nom" aria-label="lastName" :value="ctc.lastName">
            <input type="text" class="form-control" placeholder="Prénom" aria-label="firstName"
                   :value="ctc.firstName">
          </div>


          <div class="input-group mt-5 mb-3 d-flex justify-content-center">
            <button @click="removeMail" class="btn btn-danger" type="button">
              <font-awesome-icon icon="minus"></font-awesome-icon>
            </button>
            <span class="input-group-text"><strong>Email</strong></span>
            <button @click="addMail" class="btn btn-success" type="button">
              <font-awesome-icon icon="plus"></font-awesome-icon>
            </button>
          </div>

          <template v-for="email in ctc.emails">
            <div class="input-group mb-3">
              <span class="input-group-text"><font-awesome-icon icon="envelope"></font-awesome-icon></span>
              <input type="text" class="form-control" placeholder="Email" aria-label="email" :value="email.email">
            </div>
          </template>

          <div class="input-group mt-5 mb-3 d-flex justify-content-center">
            <button @click="removeAddr" class="btn btn-danger" type="button">
              <font-awesome-icon icon="minus"></font-awesome-icon>
            </button>
            <span class="input-group-text"><strong>Adresse</strong></span>
            <button @click="addAddr" class="btn btn-success" type="button">
              <font-awesome-icon icon="plus"></font-awesome-icon>
            </button>
          </div>

          <template v-for="addr in ctc.address">
            <div class="input-group mb-3">
              <span class="input-group-text"><font-awesome-icon
                  icon="envelope"></font-awesome-icon></span>

              <input type="text" v-model="addr.street" class="form-control col-6" placeholder="Adresse"
                     aria-label="street">
              <input type="text" v-model="addr.postalCode" class="form-control" placeholder="Code postal"
                     aria-label="postalCode">
              <input type="text" v-model="addr.city" class="form-control" placeholder="Ville" aria-label="city">

            </div>
          </template>


        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
          <button type="button" class="btn btn-primary">Sauvegarder</button>
        </div>
      </div>
    </div>
  </div>


</template>

<script>
import {Modal} from 'bootstrap'

export default {

  name: "AddOrModifyModal",
  props: ['contact'],
  computed: {
    ctc: {
      get: function () {
        return this.contact || {
          firstName: "",
          lastName: "",
          address: [
            {
              postalCode: "",
              city: "",
              street: ""
            }
          ],
          emails: [
            {
              email: ""
            }
          ]
        }
      },
      set: function (newValue) {
        console.log(newValue)
        this.contact = newValue
      }
    }
  },
  methods: {
    showModal() {
      let modalDom = document.getElementById('addModal')
      let myModal = new Modal(modalDom)
      myModal.show()
    },
    addAddr() {
      this.ctc.address.push({
        postalCode: "",
        city: "",
        street: ""
      })
    },
    removeAddr() {
      if (this.ctc.address.length > 1)
        this.ctc.address.pop()
    },
    addMail() {
      this.ctc.emails.push({
        email: "",
      })
    },
    removeMail() {
      if (this.ctc.emails.length > 1)
        this.ctc.emails.pop()
    }
  }
}
</script>

<style scoped>

</style>