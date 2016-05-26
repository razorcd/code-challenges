def squareRoots(l, k)
    arr= (1..l).to_a.reverse

    k.times do
      index= index_of_max arr
      arr[index]= sqrt(arr[index])
    end  
    
    return arr.inject(&:+)    
end


def sqrt val
  Math.sqrt(val).to_i
end


def index_of_max arr
  max_val= 0
  max_index= 0
  arr.each_with_index do |val, index| 
      if max_val< val
        max_index= index
        max_val= val
      end
  end
  return max_index
end
