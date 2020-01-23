<template>
    <div class="questionComp-main">
      <div class="questionComp-main-problem">
        <el-row type="flex" justify="center" align="center">
            <slot name="questionSlot" v-for="item in question" :questionItem="item"></slot>
        </el-row>
      </div>
      <div class="questionComp-main-btn">
        <el-row type="flex" justify="center" :gutter="20">
          <el-button @click="handlerClick('up')" size="small" type="primary">上一题</el-button>
          <el-button @click="handlerClick('next')" size="small" type="primary">下一题</el-button>
          <el-button @click="handlerClick('rest')" size="small" type="primary">重置</el-button>
          <el-button @click="handlerClick('submit')" size="small" type="primary">提交</el-button>
        </el-row>

      </div>
    </div>
</template>

<script>
    export default {
        name: "questionComp",
        data(){
          return{
            question:this.$store.state.questionStore.questionList
          }
        },
      methods:{
        handlerClick(type){
          if (type=='up'){
            this.$store.commit('setCurrentPage',-1)
          }
          if (type=='next') {
            this.$store.commit('setCurrentPage',1)
          }
        }
      }
    }
</script>

<style scoped>

</style>
