<template>
<div>
  {{username}}
</div>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "index",
  data(){
    return{
      username:''
    }
  },
  methods:{
    getUsername:function (){
      this.username = this.$store.getters.getUser.username;
    }
  },
  created() {
    this.getUsername();
  }
}
</script>

<style scoped>

</style>