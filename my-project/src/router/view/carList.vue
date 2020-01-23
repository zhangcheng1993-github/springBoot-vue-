<template>
  <div class="carList-main">

    <el-row type="flex" justify="start">
      <el-col :span="24">
        <div class="carList-main-containerCls">
          <el-select clearable filterable size="small" v-model="nowSelect" placeholder="请选择">
            <el-option v-for="item in dictList" :key="item.id" :label="item.name" :value="item.name"></el-option>
          </el-select>
          <el-button type="primary" @click="search"  size="small" icon="el-icon-search">搜索</el-button>
        </div>
      </el-col>
    </el-row>

    <el-row type="flex" justify="center">
      <el-col :span="24">
        <template v-if="carlist.length!=0">
          <el-table :data="dictList" style="width: 100%" stripe border @selection-change="handleSelectionChange" show-summary sum-text="合计">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column type="index"></el-table-column>
            <el-table-column align="center" label="商品" prop="name">
              <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                  <p>名称: {{ scope.row.name }}</p>
                  <p>价格: {{ scope.row.sales }}</p>
                  <div slot="reference" class="name-wrapper">
                    <el-tag size="medium">{{ scope.row.name }}</el-tag>
                  </div>
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column align="center" label="品牌" prop="brand"></el-table-column>
            <el-table-column align="center" label="价格" prop="sales" sortable></el-table-column>
            <el-table-column align="center" label="数量" prop="count" sortable>
              <template slot-scope="scope">
                <div slot="reference" class="name-wrapper">
                  <el-input type="number" clearable max=100 min=0 v-model.lazy.number="scope.row.count" placeholder="请输入数量"></el-input>
                </div>
              </template>
            </el-table-column>
            <el-table-column align="center" label="总价" prop="total" sortable></el-table-column>
          </el-table>
        </template>
        <template v-else>
          <el-alert title="购物车暂时没有记录!"type="warning"></el-alert>
        </template>
      </el-col>
    </el-row>

    <el-row  type="flex"  :gutter="20" justify="start" style="margin: 15px">
      <el-col :span="6">
          <el-input v-model="input"  size="small" placeholder="请输入优惠码"></el-input>
      </el-col>
      <el-col :span="4">
           <el-button type="primary"  size="small" @click="handleYouhui">使用优惠码</el-button>
      </el-col>
      <el-col :span="3">
        <el-button type="primary"  size="small" @click="shop">结算</el-button>

        <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
          <span>您购买的商品是:</span>
          <div v-for="multipitem in nowMultipleSelection">{{multipitem.name}}</div>
          <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
        </el-dialog>

      </el-col>
      <el-col :span="9"></el-col>
      <el-col :span="6" >
          <div class="carList-total">
            <div style="color: #e38d13">当前商品原价:{{total}}</div>
            <div style="color: #e38d13">当前优惠价:{{youhui}}</div>
            <div style="color: #e38d13">优惠后价格:{{total-youhui}}</div>
          </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "carList",
    data(){
      return{
        carlist:this.$store.state.shopStore.carList,
        productList:this.$store.state.shopStore.productList,
        multipleSelection: [],
        nowMultipleSelection: [],
        input:'',
        youhui:0,
        dialogVisible:false,
        maxNum:5,
        minNum:0,
        nowSelect:'',
        fiflerSelect:''
      }
    },
    computed:{
      dictList(){
        var _this=this;
        var dict={};
        this.productList.forEach((item)=>{
          dict[item.id]=item;
        });
        var dictCar=this.carlist.map((item)=>{
          item.name=dict[item.id].name;
          item.brand=dict[item.id].brand;
          item.image=dict[item.id].image;
          item.sales=dict[item.id].sales;
          item.color=dict[item.id].color;
          item.total=dict[item.id].sales*item.count;
          return item;
        });
        if (_this.fiflerSelect!=''){
          dictCar=dictCar.filter((item)=>item.name==_this.fiflerSelect);
        }
        return dictCar;
      },
      total(){
        var total=0;
        this.multipleSelection.filter((item)=>{
          total+=item.sales*item.count
        });
        return total;
      }
    },
    methods:{
      handleSelectionChange(val){
        this.multipleSelection = val;
      },
      shop(){
        var _this=this;
        this.$confirm('此操作将购买选中的商品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          _this.$store.commit('restCar',this.multipleSelection);
          _this.dialogVisible=true;
          _this.nowMultipleSelection=_this.multipleSelection;
          _this.multipleSelection=[];
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消购买'
          });
        });
      },
      handleYouhui(){
        if (this.total<=0){
          this.$message({
            message: '请先购买商品!',
            type: 'warning',
            duration:1000
          });
          return false;
        }

        if (this.input!='vue.js'){
          this.$message({
            message: '优惠码错误',
            type: 'warning',
            duration:500
          });
          this.input='';
        }else{

          if (this.youhui>0){
            this.$message({
              message: '已经优惠过了,不能再使用！',
              type: 'warning',
              duration:500
            });
            return false;
          }
          this.youhui=500;
        }
      },
      search(){
          this.fiflerSelect=this.nowSelect;
      }
    },
    watch:{
      dictList:{
        handler(val){
          var _this=this;;
          val.forEach((item)=>{
            if (item.count>_this.maxNum){
              item.count=_this.maxNum
                this.$message({
                  message: '超出范围,最大值为5!',
                  type: 'warning',
                  duration:1000
                });
            };
            if (item.count<_this.minNum){
              item.count=_this.minNum
              this.$message({
                message: '超出范围,最小值为0!',
                type: 'warning',
                duration:1000
              });
            };
          });
        },deep:true
      }
    }
  }
</script>

<style scoped>
  .carList-total{
    margin: 10px auto;
    text-align: right;
    vertical-align: middle;
  }
  .carList-span{
    margin: 10px;
    text-align: left;
  }

  .carList-main-containerCls{
    margin: 10px auto;
  }

</style>
