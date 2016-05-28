class Board
  def initialize m:, n:, mcosts:, ncosts:
    @mcosts= []
    @ncosts= []

    mcosts.each_with_index do |v, i|
      @mcosts<< {
        value: v,
        horizontal: true,
      }
    end

    ncosts.each_with_index do |v, i|
      @ncosts<< {
        value: v,
        horizontal: false,
      }
    end
  end

  def cut
    initial_costs= []
    (@mcosts+@ncosts).each_with_index do |c,i|
      c[:position]= i
      initial_costs<< c
    end

    best_cost= 99999999999999
    # puts initial_costs.to_s
    initial_costs.permutation do |permuted_costs|
      costs= permuted_costs.map {|c| c.dup}
      # puts costs.map {|c| c[:value]}.to_s
    # costs= initial_costs.dup

    current_cost= 0
      for i in (0..costs.length-1)
        current_cost+= costs[i][:value]
        # puts current_cost
        # puts
        for j in (i+1..costs.length-1)
          costs[j][:value]+= initial_costs[costs[j][:position]][:value] if costs[i][:horizontal] && costs[j][:horizontal].!
          costs[j][:value]+= initial_costs[costs[j][:position]][:value] if costs[i][:horizontal].! && costs[j][:horizontal]
        end
      end
      # current_cost= costs.map {|c| c[:value]}.inject(&:+)
      best_cost= current_cost if current_cost< best_cost
    end
    best_cost
  end

private



end
