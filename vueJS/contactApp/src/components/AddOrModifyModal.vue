<template>


  <!-- Modal -->
  <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addOrModifyModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addOrModifyModal">Ajouter un contact</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <!--          body-->

          <div class="input-group mb-3">
            <span class="input-group-text"><font-awesome-icon icon="user"></font-awesome-icon></span>
            <input type="text" class="form-control" placeholder="Nom" aria-label="lastName" :value="contact.lastName">
            <input type="text" class="form-control" placeholder="Prénom" aria-label="firstName"
                   :value="contact.firstName">
          </div>

          <template v-for="email in contact.emails">
            <div class="input-group mb-3">
              <span class="input-group-text"><font-awesome-icon icon="envelope"></font-awesome-icon></span>
              <input type="text" class="form-control" placeholder="Email" aria-label="email" :value="contact.lastName">
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
  // data() {
  //   return {
  //     contact: this.contact || {
  //       firstName: "",
  //       lastName: "",
  //       address: [
  //         {
  //           postalCode: "",
  //           city: "",
  //           street: ""
  //         }
  //       ],
  //       emails: [
  //         {
  //           email: ""
  //         }
  //       ]
  //     }
  //   }
  // },
  methods: {
    showOrHideModal() {
      let modalDom = document.getElementById('addModal')
      let myModal = new Modal(modalDom)
      if (modalDom.classList.contains('show'))
        myModal.hide()
      else
        myModal.show()
    }
  }
}
</script>

<style scoped>

</style>