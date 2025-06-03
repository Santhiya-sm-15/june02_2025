class pair
{
    int val,ind,candy;
    public pair(int val,int ind)
    {
        this.val=val;
        this.ind=ind;
        this.candy=0;
    }
}
class Solution {
    public int candy(int[] ratings) {
        int n=ratings.length;
        if(n==1) return 1;
        pair[] arr=new pair[n];
        Map<Integer,pair> m=new HashMap<>();
        for(int i=0;i<n;i++)
        {
            arr[i]=new pair(ratings[i],i);
            m.put(i,arr[i]);
        }
        Arrays.sort(arr,(a,b)->{
            if(a.val==b.val)
                return Integer.compare(a.ind,b.ind);
            return Integer.compare(a.val,b.val);
        });
        int cnt=0;
        for(int i=0;i<n;i++)
        {
            int r=arr[i].val;
            int ind=arr[i].ind;
            int x=0;
            if(ind-1>=0)
            {
                pair p=m.get(ind-1);
                if(p.val>=r)
                    x=Math.min(arr[i].candy,p.candy)+1;
                else
                    x=Math.max(arr[i].candy,p.candy)+1;
            }
            if(ind+1<n)
            {
                pair p=m.get(ind+1);
                if(p.val>=r)
                {
                    x=Math.max(x,Math.min(arr[i].candy,p.candy)+1);
                }
                else
                {
                    x=Math.max(x,Math.max(arr[i].candy,p.candy)+1);
                }
            }
            arr[i].candy=x;
            cnt+=arr[i].candy;
        }
        return cnt;
    }
}