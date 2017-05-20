using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using WebShop.DL.DataLayerContract;
using WebShop.DL.DataLayerContract.Entities;

namespace WebShop.DL.EFDataLayer
{
    public class EFDataAccess<T> : IDataAccess<T>
        where T : class, IEntity
    {
        private DbSet<T> _dbSet;

        public EFDataAccess(WebShopContext ctx)
        {
            _dbSet = ctx.Set<T>();
        }

        public IQueryable<T> Read()
        {
            return _dbSet;
        }

        public void Add(T entity)
        {
            _dbSet.Add(entity);
        }

        public void Update(T entity)
        {
            _dbSet.Attach(entity);
        }

        public void Delete(T entity)
        {
            _dbSet.Remove(entity);
        }
    }
}
