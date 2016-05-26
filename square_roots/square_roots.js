function squareRoots(l, k) {
  var arr=generate_array(l);

  for(var i=1; i<=k; i++) {
    var index= index_of_max(arr);
    arr[index]= parseInt(Math.sqrt(arr[index]));
  }

  return sum(arr);
}

function index_of_max(arr) {
  var max_val= 0;
  var max_index= 0;
    for(var i=0;i<arr.length;i++) {
      if (max_val < arr[i]) {
        max_val= arr[i];
        max_index= i;
      }
    }
  return max_index;
}

function sum(arr) {
  var sum=0;
  for(i=0;i<arr.length;i++) {
    sum+= arr[i];
  }
  return sum;
}

function generate_array(limit){
  var arr=[];
  for(var i=1; i<=limit; i++){ arr[i-1]=i; }
  return arr;
}
