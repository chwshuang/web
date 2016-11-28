/*
*  AngularJs Fullcalendar Wrapper for the JQuery FullCalendar
*  API @ http://arshaw.com/fullcalendar/
*
*  Angular Calendar Directive that takes in the [eventSources] nested array object as the ng-model and watches it deeply changes.
*       Can also take in multiple event urls as a source object(s) and feed the events per view.
*       The calendar will watch any eventSource array and update itself when a change is made.
*
*/


//-----------
angular.module('ui.system', ['tm.pagination']).controller('testController', ['$scope', '$http', '$modal', function($scope, $http, $modal){
   // 在变更分布的时候，重新获取数据条目
   var reGetProducts = function(){
      // 发送给后台的请求数据
      var postData = {
         currentPage: $scope.paginationConf.currentPage,
         itemsPerPage: $scope.paginationConf.itemsPerPage
      };
      var data = {
             currentPage: $scope.paginationConf.currentPage,
             itemsPerPage: $scope.paginationConf.itemsPerPage
          },
//post请求的地址
          url = "http://localhost:8080/user/getUserList",
//将参数传递的方式改成form
          postCfg = {
             headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
             transformRequest: function (data) {
                return $.param(data);
             }
          };
         $http.post(url, data, postCfg).success(function (data) {
                $scope.paginationConf.totalItems = data.total;
                $scope.list = data.data;
          });
   };

    /** 定义一个单击删除按钮时触发的事件，用于删除选中行 **/
    $scope.delete = function ($index) {
        if($index>=0){
            if(confirm("是否删除["+$scope.list[$index].username+"]用户") ){
                $scope.list.splice($index,1);
            }
        }
    };

    var idx = -1;

    //定义一个单击修改按钮时触发的事件，用于单击后弹出模块窗口用于修改数据
    $scope.upData = function ($index) {
        var modalInstance = $modal.open({
            templateUrl: 'myModalContent.html',
            controller: 'SystemCtrl',
            size: null,
            resolve: {
                items: function () {
                    return $scope.list[$index];
                }
            }
        });

        // modalInstance.result.then(function (selectedItem) {
        //     $scope.selected = selectedItem;
        // }, function () {
        //     console.log('Modal dismissed at: ' + new Date());
        // });
        // $scope.selected = {
        //     item: $scope.items[0]
        // };

        //将选中行的数据绑定到临时对象prod中，在下面的模态窗口中展示出来
        // $scope.editUserid = $scope.list[$index].id;
        // $scope.editUser.name = $scope.list[$index].username;
        // //选中行的索引赋值给全局变量idx
        // idx = $index;
    };

    //定义一个单机保存按钮时触发的事件,
    $scope.save = function () {
        //将修改后的值赋给数组
        $scope.list[idx].id = $scope.item.id;
        $scope.list[idx].name = $scope.item.name;
        //关闭模块窗口
        $('#modal-1').modal('hide');
    }


    $scope.items = ['item1', 'item2', 'item3'];
    $scope.open = function (size) {
        var modalInstance = $modal.open({
            templateUrl: 'myModalContent.html',
            controller: 'SystemCtrl',
            size: size,
            resolve: {
                items: function () {
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
        }, function () {
            console.log('Modal dismissed at: ' + new Date());
        });
        $scope.selected = {
            item: $scope.items[0]
        };
    };

   // 配置分页基本参数
   $scope.paginationConf = {
      currentPage: 1,
      itemsPerPage: 10,
      perPageOptions: [10, 20, 30]
   };

   // 通过$watch currentPage和itemperPage 当他们一变化的时候，重新获取数据条目
   $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', reGetProducts);

}]).controller('SystemCtrl', ['$scope', '$modalInstance','$log', 'items', function($scope, $modalInstance,$log, items) {
    $scope.editUser = items;
    $log.info(items);
    // $scope.selected = {
    //     item: $scope.items[0]
    // };

    $scope.ok = function () {
        $modalInstance.close($scope.selected);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}])
;