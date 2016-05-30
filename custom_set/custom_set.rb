class CustomSet

  def initialize
    @empty= true
    @size= 0
  end

  def is_empty?
    @empty
  end

  def add val
    @size+= 1
    @empty= false
  end

  def size
    @size
  end
end
