<template>
  <div class="question-main">
    <el-container>
      <el-header>
        <span>调查问卷</span>
      </el-header>
      <el-main>
        <div class="question-main-slot">
            <questionComp>
              <template slot="questionSlot" slot-scope="props">
                <div class="question-main-slot-quesnaire" v-show="props.questionItem.id==currentPage">
                    <div class="question-main-slot-quesnaire-title">
                      <span>{{props.questionItem.id}}.{{props.questionItem.question}}</span>
                    </div>
                    <div class="question-main-slot-quesnaire-answer"  v-if="props.questionItem.modelVal=='sex'">
                      <el-radio-group v-model="sex">
                        <el-radio v-for="answer in props.questionItem.answer" :key="answer" :label="answer" >{{answer}}</el-radio>
                      </el-radio-group>
                    </div>
                  <div class="question-main-slot-quesnaire-answer"  v-if="props.questionItem.modelVal=='hobby'">
                    <el-checkbox-group v-model="hobby">
                      <el-checkbox v-for="answer in props.questionItem.answer" :key="answer" :label="answer" >{{answer}}</el-checkbox>
                    </el-checkbox-group>
                  </div>
                  <div class="question-main-slot-quesnaire-answer" v-if="props.questionItem.modelVal=='remarks'">
                    <el-input type="textarea" autosize  placeholder="请输入内容" v-model="remarks"></el-input>
                  </div>
                </div>
              </template>
            </questionComp>
        </div>
      </el-main>
      <el-footer></el-footer>
    </el-container>
  </div>
</template>

<script>
  import questionComp from '../../components/questionComp.vue';
    export default {
        name: "question",
        components:{
          questionComp
        },
      data(){
          return{
            sex:'',
            hobby:[],
            remarks:''
          }
      },
      computed:{
        currentPage(){
          return this.$store.state.questionStore.currentPage
        }
      }
    }
</script>

<style scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .question-main-slot-quesnaire-answer{
    margin: 5px auto;
  }

</style>
