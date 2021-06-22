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

          <template v-for="email in ctc.emails">
            <div class="input-group mb-3">
              <span class="input-group-text"><font-awesome-icon icon="envelope"></font-awesome-icon></span>
              <input type="text" class="form-control" placeholder="Email" aria-label="email" :value="email.email">
            </div>
          </template>


          <template v-for="addr in ctc.address">
            <div class="input-group mb-3">
              <span class="input-group-text"><font-awesome-icon
                  icon="envelope"></font-awesome-icon></span>
              <input type="text" class="form-control col-6" placeholder="Adresse" aria-label="street"
                     :value="addr.street">
              <input type="text" class="form-control" placeholder="Code postal" aria-label="postalCode"
                     :value="addr.postalCode">
              <input type="text" class="form-control" placeholder="Ville" aria-label="city" :value="addr.city">
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
    ctc() {
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
    }
  },
  methods: {
    showModal() {
      let modalDom = document.getElementById('addModal')
      let myModal = new Modal(modalDom)
      myModal.show()
    },
    test() {
      this.ctc.address.push({
        postalCode: "",
        city: "",
        street: ""
      })
    }
  }
}
</script>

<style scoped>

</style>