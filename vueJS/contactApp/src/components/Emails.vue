<template>
  <div class="accordion">
    <div class="accordion-item">
      <h2 class="accordion-header" id="panelsStayOpen-headingOne">
        <button class="accordion-button" type="button" data-bs-toggle="collapse"
                :data-bs-target="`[id='${emails[0]._id}']`" aria-expanded="true" :aria-controls="(emails[0]._id)">
          Emails :
        </button>
      </h2>
      <div :id="emails[0]._id" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
        <div class="accordion-body">
          <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
              <li class="list-group-item" v-for="{ email } in emails">{{ email }}</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "Emails",
  props: ['emails']

}
</script>

<style scoped>

</style>