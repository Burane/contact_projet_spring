<template>
  <div class="accordion">
    <div class="accordion-item">
      <h2 class="accordion-header" id="panelsStayOpen-headingOne">
        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                :data-bs-target="`[id='${id}']`" aria-expanded="false" :aria-controls="(id)">
          Addresses :
        </button>
      </h2>
      <div :id="id" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingOne">
        <div class="accordion-body">
          <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
              <li class="list-group-item" v-for="{city, street, postalCode} in address">{{ street }} {{ postalCode }}
                {{ city }}
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "Address",
  props: ['infos'],
  data() {
    return {
      id : this.infos.id,
      address : this.infos.address
    }
  }

}
</script>

<style scoped>
.collapse {
  visibility: hidden;
}
.collapse.show {
  visibility: visible;
  display: block;
}
.collapsing {
  position: relative;
  height: 0;
  overflow: hidden;
  -webkit-transition-property: height, visibility;
  transition-property: height, visibility;
  -webkit-transition-duration: 150ms;
  transition-duration: 150ms;
  -webkit-transition-timing-function: ease;
  transition-timing-function: ease;
}
.collapsing.width {
  -webkit-transition-property: width, visibility;
  transition-property: width, visibility;
  width: 0;
  height: auto;
}
</style>