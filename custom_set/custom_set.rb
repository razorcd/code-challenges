class CustomSet

  def initialize
    @empty= true
  end

  def is_empty?
    @empty
  end

  def add val
    @empty= false
  end
end
