<template>
<div>{{ userInfo }}</div>
</template>

<script>
export default {
  name: "userInfo",
  data(){
    return{
      userInfo:''
    }
  },
  methods:{
    loadUserInfo(){
      this.userInfo = this.$store.getters.getUser;
    }
  },
  created() {
    this.loadUserInfo();
  }
}
</script>

<style scoped>

</style>
