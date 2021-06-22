<template>
  <div class="d-flex justify-content-center">
    <button class="btn btn-circle btn-xl btn-primary" data-bs-toggle="modal" data-bs-target="#addModal">
      <font-awesome-icon icon="plus"></font-awesome-icon>
    </button>
  </div>

  <!-- Modal -->
  <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="addModalLabel">Ajouter un contact</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <!--          body-->

          <div class="input-group  mb-3">
            <span class="input-group-text"><font-awesome-icon icon="user"></font-awesome-icon></span>
            <input type="text" class="form-control" placeholder="Nom" aria-label="lastName" :value="contact.lastName">
            <input type="text" class="form-control" placeholder="Prénom" aria-label="firstName"
                   :value="contact.firstName">
          </div>


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
export default {
  name: "AddContact",
  props: ['contact'],
  data() {
    return {
      contact: this.contact || {
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
  }
}
</script>

<style scoped>

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