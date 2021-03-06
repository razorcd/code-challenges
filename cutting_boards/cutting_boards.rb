class Board
  def initialize mcosts:, ncosts:
    @mcosts= mcosts
    @ncosts= ncosts
  end

  def cut
    initial_costs= all_costs_as_obj

    best_cost= Float::INFINITY
    permutations_of(initial_costs) do |permuted_costs|
      costs= permuted_costs.map {|c| c.dup}
      current_cost= 0

      for i in (0..costs.length-1)
        current_cost+= costs[i][:value]
        for j in (i+1..costs.length-1)
          costs[j][:value]+= initial_costs[costs[j][:position]][:value] if costs[i][:horizontal] && costs[j][:horizontal].!
          costs[j][:value]+= initial_costs[costs[j][:position]][:value] if costs[i][:horizontal].! && costs[j][:horizontal]
        end
      end

      best_cost= current_cost if current_cost< best_cost
    end
    best_cost
  end

private

  def all_costs_as_obj
    mcosts= []
    ncosts= []

    @mcosts.each_with_index do |v, i|
      mcosts<< {
        value: v,
        horizontal: true,
      }
    end

    @ncosts.each_with_index do |v, i|
      ncosts<< {
        value: v,
        horizontal: false,
      }
    end

    initial_costs= []
    (mcosts+ncosts).each_with_index do |c,i|
      c[:position]= i
      initial_costs<< c
    end

    initial_costs
  end

  def permutations_of arr, &block
    _permutation arr.dup, [], &block
  end

  def _permutation arr, current, &block
    for i in (0..arr.length-1)
      val= arr.slice!(i)
      current.push(val)
      yield(current) if arr.empty?
      _permutation arr, current, &block
      arr.insert(i, val)
      current.pop
    end
  end
end
